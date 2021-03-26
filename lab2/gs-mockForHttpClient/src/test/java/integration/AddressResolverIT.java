package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import exceptions.BadArrayIndex;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolver resolver;
    private ISimpleHttpClient httpClient;

    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException, BadArrayIndex {
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );

    }

    @Test
    public void whenBadCoordidates_throwBadArrayindex() throws IOException, URISyntaxException, ParseException {
        assertThrows(BadArrayIndex.class,() -> resolver.findAddressForLocation(-190,-190));
    }

    @Test
    public void whenNullURLthrowException() throws IOException, URISyntaxException, ParseException {

        assertThrows(NullPointerException.class, () -> {
            httpClient.get((null));
        });
        assertThrows(IOException.class, () -> {
            httpClient.get((""));
        });
    }

}
