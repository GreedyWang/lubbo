package registry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MockServiceRegistry implements ServiceRegistry,ServiceDiscovery {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockServiceRegistry.class);

    private String registryAddress;
    private File file;

    public MockServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
        file = new File("/register.txt");
    }


    @Override
    public void register(String data) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(data.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String discover() {
        FileReader in = null;
        String context = "";
        try {
            in = new FileReader(file);
            BufferedReader read = new BufferedReader(in);
            context = read.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context;
    }
}
