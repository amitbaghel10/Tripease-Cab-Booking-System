package com.example.tripease.Service;

import com.example.tripease.DTO.Request.BookingRequest;
import com.example.tripease.DTO.Response.BookingResponse;
import com.example.tripease.Exception.CabUnavailableException;
import com.example.tripease.Exception.CustomerNotFoundException;
import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Model.Booking;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Customer;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.BookingRepository;
import com.example.tripease.Repository.CabRepository;
import com.example.tripease.Repository.CustomerRepository;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//for email 22/09/2026
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.text.SimpleDateFormat;

import java.awt.print.Book;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        Customer customer = optionalCustomer.get();
        Cab availableCab = cabRepository.getAvailableCabRandomly();

        if(availableCab == null){
            throw new CabUnavailableException("Sorry! No Cab is available at this moment");
        }

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest, availableCab.getPerKmRate());
        Booking savedBooking = bookingRepository.save(booking);
        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);

        /*Driver driver = driverRepository.getDriverByCabId(availableCab.getCabId());
        driver.getBookings().add(savedBooking);
*/

        //for email 22/09/2026
        Driver driver =
                driverRepository.getDriverByCabId(availableCab.getCabId());

        if (driver == null) {
            throw new DriverNotFoundException(
                    "No driver found for Cab ID: " + availableCab.getCabId()
            );
        }

        driver.getBookings().add(savedBooking);

        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

//        sendEmail(savedCustomer);

        //for email 22/09/2026
        sendEmail(
                savedCustomer,
                savedBooking,
                availableCab,
                savedDriver
        );

        return BookingTransformer.bookingToBookingResponse(savedBooking,savedCustomer,availableCab,savedDriver);


    }

    /*private void sendEmail(Customer customer){
        String text = "Congrats!! "+ customer.getName() + ". Your Cab has been booked";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("javaxspring@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab booked");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }*/

    //for email 22/09/2026
    private void sendEmail(Customer customer,
                           Booking booking,
                           Cab cab,
                           Driver driver) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("javaxspring@gmail.com");
            helper.setTo(customer.getEmailId());

            helper.setSubject("Tripease Booking Confirmation - Booking #"
                    + booking.getBookingId());

            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("dd MMM yyyy, hh:mm a");

            String bookingDate =
                    dateFormat.format(booking.getBookedAt());

            String htmlContent = """

                <html>
                <body style="font-family: Arial, sans-serif;
                             background-color: #f4f6f8;
                             margin: 0;
                             padding: 30px;">

                    <div style="max-width: 650px;
                                margin: auto;
                                background-color: white;
                                border-radius: 10px;
                                padding: 30px;
                                box-shadow: 0 2px 10px rgba(0,0,0,0.08);">

                        <h2 style="color: #1a73e8;
                                   margin-bottom: 5px;">
                            Tripease
                        </h2>

                        <h3 style="color: #333;">
                            Booking Confirmed!
                        </h3>

                        <p style="color: #555;">
                            Hello <strong>%s</strong>,
                        </p>

                        <p style="color: #555;">
                            Your cab has been successfully booked.
                            Here are your trip details:
                        </p>

                        <hr>

                        <h3 style="color: #333;">
                            Booking Details
                        </h3>

                        <table style="width: 100%%;
                                      border-collapse: collapse;
                                      margin-top: 10px;">

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Booking ID
                                </td>

                                <td style="padding: 8px;
                                           font-weight: bold;">
                                    #%d
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Booking Date
                                </td>

                                <td style="padding: 8px;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Status
                                </td>

                                <td style="padding: 8px;
                                           color: #16803c;
                                           font-weight: bold;">
                                    %s
                                </td>
                            </tr>

                        </table>

                        <hr>

                        <h3 style="color: #333;">
                            Trip Details
                        </h3>

                        <table style="width: 100%%;
                                      border-collapse: collapse;">

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Pickup
                                </td>

                                <td style="padding: 8px;
                                           font-weight: bold;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Destination
                                </td>

                                <td style="padding: 8px;
                                           font-weight: bold;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Distance
                                </td>

                                <td style="padding: 8px;">
                                    %.2f km
                                </td>
                            </tr>

                        </table>

                        <hr>

                        <h3 style="color: #333;">
                            Cab & Driver Details
                        </h3>

                        <table style="width: 100%%;
                                      border-collapse: collapse;">

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Cab Number
                                </td>

                                <td style="padding: 8px;
                                           font-weight: bold;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Cab Model
                                </td>

                                <td style="padding: 8px;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Driver Name
                                </td>

                                <td style="padding: 8px;
                                           font-weight: bold;">
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 8px;
                                           color: #777;">
                                    Driver Email
                                </td>

                                <td style="padding: 8px;">
                                    %s
                                </td>
                            </tr>

                        </table>

                        <hr>

                        <div style="background-color: #f1f8ff;
                                    padding: 15px;
                                    border-radius: 8px;
                                    margin-top: 20px;">

                            <table style="width: 100%%;">

                                <tr>
                                    <td style="font-size: 18px;
                                               font-weight: bold;">
                                        Total Fare
                                    </td>

                                    <td style="font-size: 20px;
                                               font-weight: bold;
                                               text-align: right;
                                               color: #1a73e8;">
                                        ₹%.2f
                                    </td>
                                </tr>

                            </table>

                        </div>

                        <p style="color: #777;
                                  font-size: 13px;
                                  margin-top: 25px;">

                            Thank you for choosing
                            <strong>Tripease</strong>.

                        </p>

                        <p style="color: #999;
                                  font-size: 12px;">

                            This is an automated booking confirmation.
                            Please do not reply to this email.

                        </p>

                    </div>

                </body>
                </html>

                """.formatted(
                    customer.getName(),
                    booking.getBookingId(),
                    bookingDate,
                    booking.getTripStatus(),
                    booking.getPickup(),
                    booking.getDestination(),
                    booking.getTripDistanceInKm(),
                    cab.getCabNumber(),
                    cab.getCabModel(),
                    driver.getName(),
                    driver.getEmailId(),
                    booking.getBillAmount()
            );

            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {

            throw new RuntimeException(
                    "Unable to send booking confirmation email", e
            );
        }
    }
}
