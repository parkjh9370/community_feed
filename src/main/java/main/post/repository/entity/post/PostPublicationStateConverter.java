package main.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import main.post.domain.content.PostPublicationState;

public class PostPublicationStateConverter implements
    AttributeConverter<PostPublicationState, String> {


    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state != null ? state.name() : "";
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }
}
