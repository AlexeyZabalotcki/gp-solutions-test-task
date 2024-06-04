package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.ContactsDto;

public class ContactsDtoBuilder  implements TestBuilder<ContactsDto> {

    private Long id = 1L;
    private String phone = "+375 17 309-80-00";
    private String email = "doubletreeminsk.info@hilton.com";
    @Override
    public ContactsDto build() {
        return
                ContactsDto.builder()
                        .phone(this.phone)
                        .email(this.email)
                        .build();
    }
}
