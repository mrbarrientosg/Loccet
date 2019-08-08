package util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPools {

    private enum ThreadPoolType {
        DAEMON,
        NODAEMON;
    }

    private static ThreadPools instance;

    private Map<ThreadPoolType, ExecutorService> threadPools;

    private ThreadPools() {
        threadPools = new HashMap<>();
    }

    public static ThreadPools getInstance() {
        if (instance == null)
            instance = new ThreadPools();
        return instance;
    }

    public ExecutorService fxThreadPool() {
        if (threadPools.containsKey(ThreadPoolType.NODAEMON))
            return threadPools.get(ThreadPoolType.NODAEMON);

        ExecutorService executorService = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });

        threadPools.put(ThreadPoolType.NODAEMON, executorService);

        return executorService;
    }

    public ExecutorService fxDaemonThreadPool() {
        if (threadPools.containsKey(ThreadPoolType.DAEMON))
            return threadPools.get(ThreadPoolType.DAEMON);

        ExecutorService executorService = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        threadPools.put(ThreadPoolType.DAEMON, executorService);

        return executorService;
    }

    public void shutdownThreadPools() {
        threadPools.values().forEach(ExecutorService::shutdown);
        threadPools.clear();
    }
}
