//package ru.skakunov.controllers;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import ru.skakunov.controllers.dto.AddressDto;
//import ru.skakunov.persist.CityRepository;
//import ru.skakunov.persist.StreetRepository;
//
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/search")
//public class SearchController {
//    private final CityRepository cityRepository;
//    private final StreetRepository streetRepository;
//    private final EntityManager entityManager;
//
//
//    @GetMapping
//    public ResponseEntity<AddressDto> searchForData(@RequestParam("address") String address) {
//        String[] values = address.split(",");
//
//        for (int i = 0; i < values.length; i++) {
//            for (int j = 0; j < values.length && j != i; j++) {
//                for (int k = 0; k < values.length && k != j && k != i; k++) {
//                    for (int t = 0; t < values.length && t != k && t != j && t != i; t++) {
//                        Query query = entityManager.createNativeQuery(
//                                "select city_name,street_name,house_number,apartment_number from cities c join streets s on c.id=s.city_id " +
//                                        "join houses h on h.street_id=s.id join apartments a on a.house_id=h.id" +
//                                        "  where c.city_name=:city_param and s.street_name=:street_param and h.house_number=:house_param and a.apartment_number=:apartment_param");
//                        query.setParameter("city_param", values[i]);
//                        query.setParameter("street_param", values[j]);
//                        query.setParameter("house_param", values[k]);
//                        query.setParameter("apartment_param", values[t]);
//
//                        List<Object> result = query.getResultList();
//                        if (!result.isEmpty()) {
//                            Object[] first = (Object[])result.get(0);
//                            Arrays.stream(first).map()
//
//                            return ResponseEntity.ok(new AddressDto("city_param",
//                                    "street_param",
//                                    "house_param",
//                                    "apartment_param"));
//                        }
//                    }
//                }
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//}
