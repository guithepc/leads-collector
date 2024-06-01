package com.pcaut.leads_collector.services;

import java.io.IOException;
import java.util.List;

public interface ConnectionService {
     List<String> connect() throws InterruptedException, IOException;
}
