package com.tickets.mgolimbiiev.tickets;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tickets.mgolimbiiev.tickets.Tasks.TaskToGetToken;
import com.tickets.mgolimbiiev.tickets.core.TokenParser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Миха on 19.03.2016.
 */
public class TicketService extends IntentService {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TicketService(String name) {
        super(name);
    }
    public TicketService() {
        super("MyService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Future<String> future = executor.submit(new TaskToGetToken());
        String HtmlCode = null;
        try {
            HtmlCode = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TokenParser parser = new TokenParser();
        String token = parser.parseIndexPage(HtmlCode);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
