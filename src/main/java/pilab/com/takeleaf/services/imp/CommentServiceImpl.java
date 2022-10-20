package pilab.com.takeleaf.services.imp;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pilab.com.takeleaf.models.Comment;
import pilab.com.takeleaf.repositories.CommentRepository;
import pilab.com.takeleaf.services.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Override
    public void saveComment(Comment comment) {
      commentRepo.save(comment);
        return ;
    }
}
