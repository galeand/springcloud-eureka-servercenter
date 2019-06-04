package com.sse.ticket.controller;

import com.sse.ticket.service.TicketProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketProviderController {
    @Autowired
    private TicketProviderService providerService;

    @ResponseBody
    @GetMapping("/ticket")
    public String buyTicket() {
        return providerService.buyTicket();
    }
}
