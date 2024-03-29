/*
 * Dataverse Network - A web application to distribute, share and analyze quantitative data.
 * Copyright (C) 2007
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 *  along with this program; if not, see http://www.gnu.org/licenses
 * or write to the Free Software Foundation,Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package edu.harvard.iq.dvn.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Ellen Kraffmiller
 */
public class PropertyUtil implements java.io.Serializable  {
    public static String getHostUrl() {
            String hostUrl = System.getProperty("dvn.inetAddress");
            if (hostUrl==null) {
                try {
                        hostUrl= InetAddress.getLocalHost().getCanonicalHostName();
                } catch(UnknownHostException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return hostUrl;
        }
    /**
     * Returns the value of JVM property dvn.timerServer
     * The default value of this property is true (so that in a simple single server
     * configuration, the single server is automatically the "timer server" )
     * @return
     */
    public static boolean isTimerServer() {
        boolean isTimerServer = true;
        String timerServer = System.getProperty("dvn.timerServer");
        if (timerServer!=null) {
            isTimerServer = Boolean.parseBoolean(timerServer);
        }
        return isTimerServer;

    }
    public PropertyUtil() {
      
    }

    /**
     * This method returns the hostname of the timer server, which is needed for the LOCKSS manifest page,
     * to display the OAI URL for lockss harvesting. (We may find other uses for this in the future.)
     *
     * If this instance is the timer server, returns the canonical host name.  Else, it will look for
     * a JVM option 'dvn.timerServerHost'. If this is not the timer server, and the JVM option is not set,
     * @return hostname of the timer server
     * @throws RuntimeException - Exception is thrown if this is not the timer server, and the JVM option 'dvn.timerServer' is not set.
     */
    public static String getTimerServerHost() {
        if (isTimerServer()) {
            try {
                return InetAddress.getLocalHost().getCanonicalHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            String timerServerHost = System.getProperty("dvn.timerServerHost");
            if (timerServerHost == null) {
                throw new RuntimeException("Missing JVM option: dvn.timerServerHost.  If JVM option dvn.timerServer is set to 'false', then timerServerHost must be defined.");
            }
            return timerServerHost;
        }
    }

}
