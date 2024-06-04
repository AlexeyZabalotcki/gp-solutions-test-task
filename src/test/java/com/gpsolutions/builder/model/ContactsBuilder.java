package com.gpsolutions.builder.model;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.model.Contacts;

public class ContactsBuilder implements TestBuilder<Contacts> {

    private Long id = 1L;
    private String phone = "+375 17 309-80-00";
    private String email = "doubletreeminsk.info@hilton.com";

    @Override
    public Contacts build() {
        return
                Contacts.builder()
                        .id(this.id)
                        .phone(this.phone)
                        .email(this.email)
                        .build();
    }
}
