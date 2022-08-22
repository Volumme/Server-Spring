package Alpha.alphaspring.DTO;

public interface IRequestDto<R, T> {
    R toEntity(T args);
}
