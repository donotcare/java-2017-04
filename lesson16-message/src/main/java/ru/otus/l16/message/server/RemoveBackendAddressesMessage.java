package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

import java.util.List;

public class RemoveBackendAddressesMessage extends Message {
    private List<Address> backendAddresses;

    public RemoveBackendAddressesMessage(List<Address> backendAddresses, Address to) {
        super(RemoveBackendAddressesMessage.class, null, to);
        this.backendAddresses = backendAddresses;
    }

    @Override
    public void exec(Addressee addressee) {
        BalancerService balancerService = (BalancerService) addressee;
        backendAddresses.forEach(balancerService::removeBackendAddress);
    }
}
