package ra.model.repository;

import ra.model.entity.Feedback;

import java.util.List;

public interface IFeedbackRepository {
    List<Feedback> findAll();

    void save(Feedback feedback);

    Feedback findById(Long id);

    void delete(Long id);
}
