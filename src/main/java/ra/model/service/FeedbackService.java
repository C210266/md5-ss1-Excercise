package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.FeedbackDTO;
import ra.model.entity.Feedback;
import ra.model.repository.IFeedbackRepository;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback(feedbackDTO.getId(),
                feedbackDTO.getRate(), feedbackDTO.getAuthor(),
                feedbackDTO.getContent(), feedbackDTO.getCreationDate(), feedbackDTO.getLikes());
        feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.delete(id);
    }

    public void like(Long id) {
        Feedback feedback = feedbackRepository.findById(id);
        feedback.setLikes(feedback.getLikes() + 1);
        feedbackRepository.save(feedback);
    }
}
