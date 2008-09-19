/*
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * "The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is ICEfaces 1.5 open source software code, released
 * November 5, 2006. The Initial Developer of the Original Code is ICEsoft
 * Technologies Canada, Corp. Portions created by ICEsoft are Copyright (C)
 * 2004-2006 ICEsoft Technologies Canada, Corp. All Rights Reserved.
 *
 * Contributor(s): _____________________.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"
 * License), in which case the provisions of the LGPL License are
 * applicable instead of those above. If you wish to allow use of your
 * version of this file only under the terms of the LGPL License and not to
 * allow others to use your version of this file under the MPL, indicate
 * your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do
 * not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the LGPL License."
 *
 */

package edu.harvard.hmdc.vdcnet.web.push;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Class used to maintain an overall state. This class is a
 * singleton as there should only be a single state at one time Also this class
 * will handle global network stats events
 */
public class NetworkStatsState {
    public static final String NETWORKSTATS_STATE =
            "edu.harvard.hmdc.vdcnet.web.push.NetworkStatsState";
    private static Map networkStatsMap = new Hashtable();
    private static NetworkStatsState singleton = null;
    private WeakHashMap listeners = new WeakHashMap();

    public static Map getNetworkStatsMap() {
        return networkStatsMap;
    }

    public static synchronized NetworkStatsState getInstance() {
        if (null == singleton) {
            singleton = new NetworkStatsState();
        }
        return singleton;
    }

    public void addNetworkStatsListener(NetworkStatsListener listener) {
        listeners.put(listener, null);
    }

    public void fireNetworkStatsEvent(final ReleaseEvent networkStatsEvent) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                Iterator listenerList = listeners.keySet().iterator();
                NetworkStatsListener networkStatsListener;
                while (listenerList.hasNext()) {
                    networkStatsListener = (NetworkStatsListener) listenerList.next();
                    Thread.currentThread().setContextClassLoader(
                            networkStatsListener.getClass().getClassLoader());
                    networkStatsListener.handleReleaseEvent(networkStatsEvent);
                }
            }
        });
        t.start();
    }
}
