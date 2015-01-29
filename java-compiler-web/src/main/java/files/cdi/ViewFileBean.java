package files.cdi;

import comments.dao.CommentsDao;
import comments.entities.Comment;
import common.singletons.PathsSingleton;
import common.utils.ReaderUtils;
import common.utils.WriterUtils;
import users.dao.UserDao;
import users.entities.User;
import util.FormatsUtils;
import util.MessagesNames;
import util.MessagesUtils;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by Kacper on 2014-10-18.
 */
@Named
@ViewScoped
public class ViewFileBean extends CrudBean {

    private static final long serialVersionUID = 7771793122683656934L;

    @Inject
    private UserDao userDao;

    @Inject
    private CommentsDao commentsDao;

    private User author;

    private List<Comment> commentList;

    private Comment comment;

    private int fileId;

    @PostConstruct
    private void init() {
        String fileId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        file = fileDao.getFileById(Integer.parseInt(fileId));
        commentList = commentsDao.getAllCommentsByFileId(file.getId().intValue());
        author = userDao.getUserById(file.getUserId());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PathsSingleton.getUSERS_FILES_PATH());
        sourceCode = ReaderUtils.readUserFileFrom(path, author.getUsername(), file.getFilename());
        comment = new Comment();
    }

    public void addComment() {
        final String empty = "";
        if (!getCommentText().equals(empty) || getCommentText() != null) {
            comment.setTime(new Date());
            comment.setUserId(loginBean.getId());
            comment.setFileId(file.getId());
            commentsDao.Add(comment);
            commentList = commentsDao.getAllCommentsByFileId(file.getId().intValue());
        }
        comment = new Comment();
    }

    public void deleteComment(Comment comment) {
        System.out.println(comment);
        commentsDao.Delete(comment);
        commentList = commentsDao.getAllCommentsByFileId(file.getId().intValue());
    }

    @Override
    public String saveFile() {
        file.setCreationTime(new Date());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PathsSingleton.getUSERS_FILES_PATH());
        WriterUtils.writeStringToUserFile(path, sourceCode,
                loginBean.getUsername(),
                file.getFilename());
        file = fileDao.Edit(file);

        MessagesUtils.addInfoFromResourceBundle(MessagesNames.SAVE_SUCCESSFUL);
        return null;
    }

    public Long getId() {
        return file.getId();
    }

    public Date getCreationTime() {
        return file.getCreationTime();
    }

    public String dateToString(Date date) {
        return FormatsUtils.formatTimestamp(date);
    }

    public String getUsername() {
        return author.getUsername();
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public String getCommentText() {
        return comment.getCommentText();
    }

    public void setCommentText(String commentText) {
        comment.setCommentText(commentText);
    }

    public Long getCommentAuthorId() {
        return comment.getUserId();
    }

    public void setCommentAuthorId(Long userId) {
        comment.setUserId(userId);
    }

    public String retCommentAuthorName(Long id) {
        return userDao.getUserByCommentId(id).getUsername();
    }
}
