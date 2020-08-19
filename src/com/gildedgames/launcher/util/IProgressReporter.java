package com.gildedgames.launcher.util;

import com.google.common.util.concurrent.ListenableFuture;
import com.skcraft.concurrency.ProgressObservable;

public abstract interface IProgressReporter
{
  public abstract void beginReporting(ListenableFuture<?> paramListenableFuture, ProgressObservable paramProgressObservable, String paramString);
}


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\IProgressReporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */