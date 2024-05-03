package com.example.partnerbackend.module.partner;

import com.example.partnerbackend.middleware.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/partner")
@Tag(name = "Partner", description = "Partners APIs")
public class PartnerController {
    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public ResponseEntity<Page<Partner>> getPartners(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(partnerService.getPartners(page, size));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Partner> getPartnerById(@RequestParam Long id) {
        return ResponseEntity.ok(partnerService.getPartnerById(id));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<Partner>> getPartnerByName(@RequestParam String name) {
        return ResponseEntity.ok(partnerService.findByNameContaining(name));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Partner>> getPartners() {
        return ResponseEntity.ok(partnerService.getPartners());
    }

    @PostMapping
    public ResponseEntity<Response> savePartner(@RequestBody Partner partner) {
        try{
            return ResponseEntity.ok(Response.success(partnerService.savePartner(partner)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.error(e.getMessage()));
        }
    }
    @PutMapping
    public ResponseEntity<Response> savePartner(@RequestParam Long id,@RequestBody Partner partner){
        try{
            return ResponseEntity.ok(Response.success(partnerService.savePartner(id, partner)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.error(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePartner(@RequestParam Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.ok().build();
    }
}
