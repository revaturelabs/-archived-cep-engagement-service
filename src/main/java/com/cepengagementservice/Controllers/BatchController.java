package com.cepengagementservice.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cepengagementservice.Models.Batch;
// import com.cepengagementservice.Models.Request;
// import com.cepengagementservice.Models.User;
import com.cepengagementservice.Services.BatchServices;

@RestController
@RequestMapping(value = "/batch")
public class BatchController {

    @Autowired
    private BatchServices batchServices;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        List<Batch> response = batchServices.getAllBatches();
        if (response != null) {
            return new ResponseEntity<List<Batch>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<List<Batch>>(response, HttpStatus.NO_CONTENT);
    }

    // TODO: Make get batches by user id
    // Make a GET call to CaliberAPI, after matching.
    // @GetMapping(value = "/user/")
    // public ResponseEntity<?> getBatchesByUserId(@RequestParam Integer id) {
    // List<Batch> batches = batchServices.getBatchesByUser(id);
    // if (batches != null) {
    // return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
    // }
    // return new ResponseEntity<List<Batch>>(batches, HttpStatus.NO_CONTENT);
    // }

    // What about same user and same batch.
    // BATCHES CANNOT BE UNIQUE RIGHT NOW!
    @PostMapping(value = "/simpleadd")
    public ResponseEntity<?> simpleAddBatch(@RequestBody Batch batch) {

        // Check if the relationship exists first.
        batchServices.addBatch(batch);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // @TODO implement addBatch to check API via RestTemplate
    // Always check there is a BATCH on API first.
    @PostMapping(value = "/add")
    public ResponseEntity<?> addBatch(@RequestBody Batch batch) {
        Object exists = checkBatch(batch.getBatchId());
        if (exists != null) {
            // Check if the relationship exists first.
            batchServices.addBatch(batch);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Already created", HttpStatus.CONFLICT);
    }

    // Temporary Cast to Object.
    @GetMapping(value = "/demo/getBatch/{id}")
    public Object checkBatch(@PathVariable String id) {
        // See StackOverflow Spring boot custom variables.
        String uri = System.getenv("API_HOST") + "/mock/training/batch/{params}";
        Map<String, String> anotherId = new HashMap<String, String>();
        anotherId.put("params", id);
        RestTemplate obj = new RestTemplate();
        Object fetched = obj.getForObject(uri, Object.class, anotherId);
        // ResponseEntity<Batch> response= new ResponseEntity(fetched,
        // HttpStatus.ACCEPTED);

        return fetched;
    }

    // TODO: add user to batch
    // @PostMapping(value = "/add/user")
    // public ResponseEntity<?> addUser(@RequestParam String batchId, @RequestBody
    // User user) {
    // Batch batch = batchServices.getBatchById(batchId);
    // if (batch != null) {
    // batch.addUser(user);
    // // DO a separate update method!
    // batchServices.addBatch(batch);
    // return new ResponseEntity<Batch>(batch, HttpStatus.ACCEPTED);
    // }
    // return new ResponseEntity<Batch>(batch, HttpStatus.NOT_FOUND);
    // }

}
