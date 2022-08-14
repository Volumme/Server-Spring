package Alpha.alphaspring.DTO;

public interface IResponseDto<T, R> {
    R fromEntity(T source);
}
