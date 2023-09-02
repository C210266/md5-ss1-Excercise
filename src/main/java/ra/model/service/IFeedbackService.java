package ra.model.service;

import ra.model.dto.FeedbackDTO;
import ra.model.entity.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> findAll();

    void save(FeedbackDTO feedbackDTO);

    Feedback findById(Long id);

    void delete(Long id);

    void like(Long id);
}
