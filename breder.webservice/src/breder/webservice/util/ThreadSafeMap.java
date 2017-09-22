package breder.webservice.util;

import java.util.Hashtable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeMap<K, V> extends Hashtable<K, V> implements
  IThreadSafeMap<K, V> {

  private final ReadWriteLock lockrw = new ReentrantReadWriteLock();

  private ThreadLocal<Lock> lock = new ThreadLocal<Lock>();

  @Override
  public void lockRead() {
    if (false) {
      Lock l = lockrw.readLock();
      synchronized (lockrw) {
        l.lock();
      }
      lock.set(l);
    }
  }

  @Override
  public void lockWrite() {
    if (false) {
      Lock l = lockrw.writeLock();
      System.out.println(lockrw.hashCode());
      synchronized (lockrw) {
        l.lock();
      }
      lock.set(l);
    }
  }

  @Override
  public void unlock() {
    if (false) {
      lock.get().unlock();
    }
  }

}
