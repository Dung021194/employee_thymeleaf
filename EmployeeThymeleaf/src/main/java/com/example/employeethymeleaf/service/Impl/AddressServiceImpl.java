package com.example.employeethymeleaf.service.Impl;

import com.example.employeethymeleaf.model.Address;
import com.example.employeethymeleaf.service.ICrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements ICrudService<Address>{
    private final List<Address> addresses;

    public AddressServiceImpl() {
       addresses = new ArrayList<>();
        addresses.add(new Address(1L,"Ha Noi"));
        addresses.add(new Address(2L,"Ho Chi Minh"));

    }

    @Override
    public List<Address> findAll() {
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        for (Address a : addresses){
            if (a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }

    @Override
    public void save(Address address) {
        if (address.getId() == null) {
            if (addresses.isEmpty()){
                address.setId(1L);
            }else {
                address.setId(addresses.get(addresses.size() - 1).getId() + 1);
            }
            addresses.add(address);
        } else {
           Address addressUpdate = findById(address.getId());
            if (address != null) {
                address.setName(addressUpdate.getName());
            }
        }

    }

    @Override
    public void deleteById(Long id) {
           Address address = findById(id);
            if (address != null) {
                addresses.remove(address);
            }
        }
}
