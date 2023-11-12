package com.javagroup3.javakiosk.service;
import com.javagroup3.javakiosk.entity.OrderRecord;
import com.javagroup3.javakiosk.repository.OrderRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderRecordService {
    private final OrderRecordRepository orderRecordRepository;

    public List<OrderRecord> getList(){
        return this.orderRecordRepository.findAll();
    }
    public void addOrderRecord(OrderRecord orderRecord){
        this.orderRecordRepository.save(orderRecord);
    }
}
