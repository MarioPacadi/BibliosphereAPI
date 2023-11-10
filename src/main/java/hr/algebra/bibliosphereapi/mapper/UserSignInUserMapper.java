package hr.algebra.bibliosphereapi.mapper;

import hr.algebra.bibliosphereapi.dto.UserDto;
import hr.algebra.bibliosphereapi.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSignInUserMapper {

    UserDto from(Account destination);
}
