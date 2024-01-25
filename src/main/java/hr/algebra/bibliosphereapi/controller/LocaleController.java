package hr.algebra.bibliosphereapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Controller
public class LocaleController {

    @GetMapping("/change-locale")
    public String changeLocale(@RequestParam("lang") String lang, HttpServletRequest request) {
        // Change the locale based on the selected language
        request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(lang));

        // Get the current URL
        String currentUrl = request.getRequestURL().toString();

        // Append the language parameter to the URL
        String changedUrl = currentUrl + "?lang=" + lang;

        return "redirect:" + changedUrl;
    }

    @GetMapping("/datetime")
    @ResponseBody
    public String dateTime(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {
        return date.toString() + "<br>" + datetime.toString();
    }
}
