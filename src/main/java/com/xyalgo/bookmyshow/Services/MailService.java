package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.booking.Booking;
import com.xyalgo.bookmyshow.shows.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Configuration
public class MailService {

    @Autowired
    ShowRepo showRepo;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, Booking booking, String subject) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("example@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        boolean html = true;
        helper.setText("<h3>Ticket Summary</h3>\n" +
                        "          <p>Movie-Name:" + booking.getMovieName() + "</p>" +
                        "          <p> Theatre:" + booking.getTheaterName() + "</p>" +
                        "          <p > Username: " + booking.getUserName() + "</p>" +
                        "          <p > Time: " + showRepo.findById(booking.getShowId()).get().getShow() + "</p>" +
                        "          <p > Seat Info: " + booking.getSeats() + "</p>" +
                        "         <h4> Total : " + booking.getTotal() + "</h4>" +
                        "<h3>Note:</h3>\n" +
                        "          <ol>\n" +
                        "            <li><p>Registrations/Tickets once booked cannot be exchanged, cancelled or refunded.</p></li>\n" +
                        "            <li><p>In case of Credit/Debit Card bookings, the Credit/Debit Card and Card holder must be present at the ticket counter while collecting the ticket(s).</p></li>\n" +
                        "          </ol>"

                , html);
        javaMailSender.send(message);
        System.out.println("mail send success........... ");


    }
}
