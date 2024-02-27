package de.tekup.carrentalsystembackend.controller;



import de.tekup.carrentalsystembackend.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analysis")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping("/find-number-car-by-brand")
    public ResponseEntity<?> findNumberCarByBrand() {
        return ResponseEntity.ok(analysisService.findNumberCarByBrand());
    }


    @GetMapping("/chart-data")
    public ResponseEntity<List<Object[]>> getChartData() {
        List<Object[]> chartData = analysisService.getChartData();
        return ResponseEntity.ok(chartData);
    }


}
