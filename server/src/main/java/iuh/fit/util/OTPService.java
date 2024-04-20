package iuh.fit.util;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OTPService extends Remote {
    public String createRandomOTP() throws RemoteException;

    public void addToOTPMap(String phoneNumber, String otp) throws RemoteException;

    public String sendSMS(String toPhoneNumber) throws RemoteException;

    public boolean checkOTP(String phoneNumber, String otp) throws RemoteException;
}
