package com.tasneem.currencyrate.repository;

import com.tasneem.currencyrate.model.ConversionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRecordRepository extends JpaRepository<ConversionRecord, Long> {}
