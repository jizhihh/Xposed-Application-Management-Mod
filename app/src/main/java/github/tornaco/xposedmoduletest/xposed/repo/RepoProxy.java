package github.tornaco.xposedmoduletest.xposed.repo;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import github.tornaco.xposedmoduletest.util.Singleton;
import github.tornaco.xposedmoduletest.xposed.util.XposedLog;
import lombok.Getter;

/**
 * Created by guohao4 on 2017/12/11.
 * Email: Tornaco@163.com
 */

public class RepoProxy {

    private static final Singleton<RepoProxy> sProxy =
            new Singleton<RepoProxy>() {
                @Override
                protected RepoProxy create() {
                    return new RepoProxy();
                }
            };

    public static RepoProxy getProxy() {
        return sProxy.get();
    }

    @Getter
    private StringSetRepo boots, starts, lks, rfks, perms;

    @Getter
    private StringSetRepo comps;

    private RepoProxy() {
        HandlerThread hr = new HandlerThread("Repo proxy");
        hr.start();
        Handler h = new Handler(hr.getLooper());

        ExecutorService io = Executors.newSingleThreadExecutor();

        File systemFile = new File(Environment.getDataDirectory(), "system");
        File dir = new File(systemFile, "tor");

        boots = new StringSetRepo(new File(dir, "boots"), h, io);
        starts = new StringSetRepo(new File(dir, "starts"), h, io);
        lks = new StringSetRepo(new File(dir, "lks"), h, io);
        rfks = new StringSetRepo(new File(dir, "rfks"), h, io);
        comps = new StringSetRepo(new File(dir, "comps"), h, io);
        perms = new StringSetRepo(new File(dir, "perms"), h, io);
    }

    public void deleteAll() {
        XposedLog.wtf("deleteAll data...");
        boots.removeAll();
        starts.removeAll();
        lks.removeAll();
        rfks.removeAll();
        perms.removeAll();
    }
}