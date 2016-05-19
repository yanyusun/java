package com.dqys.business.service.facade;

import com.dqys.business.orm.dto.ContactsInfo;

/**
 * Created by pan on 16-5-19.
 */
public interface ContactsService {

    ContactsInfo add_tx(ContactsInfo contactsInfo) throws Exception;
}
