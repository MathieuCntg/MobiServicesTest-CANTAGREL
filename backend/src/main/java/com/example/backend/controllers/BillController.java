package com.example.backend.controllers;

import com.example.backend.domain.bill.Bill;
import com.example.backend.domain.bill.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{clientId}")
    List<Bill> getPaidBillByClientId(@PathVariable UUID clientId){
        return billService.getPaidBillByClientId(clientId);
    }
}
