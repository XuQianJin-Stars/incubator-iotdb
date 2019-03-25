package org.apache.iotdb.cluster.utils;

import com.alipay.sofa.jraft.RouteTable;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.option.CliOptions;
import com.alipay.sofa.jraft.rpc.impl.cli.BoltCliClientService;
import java.util.concurrent.TimeoutException;
import org.apache.iotdb.cluster.exception.RaftConnectionException;

public class RaftUtils {

  /**
   * Get leader node according to the group id
   *
   * @param groupId group id of raft group
   * @return PeerId of leader
   */
  public static PeerId getLeader(String groupId) throws RaftConnectionException {
    Configuration conf = getConfiguration(groupId);
    RouteTable.getInstance().updateConfiguration(groupId, conf);

    final BoltCliClientService cliClientService = new BoltCliClientService();
    cliClientService.init(new CliOptions());
    try {
      if (!RouteTable.getInstance().refreshLeader(cliClientService, groupId, 1000).isOk()) {
        throw new RaftConnectionException("Refresh leader failed");
      }
    } catch (InterruptedException|TimeoutException e) {
      throw new RaftConnectionException("Refresh leader failed");
    }
    return RouteTable.getInstance().selectLeader(groupId);
  }


  public static Configuration getConfiguration(String groupID) {
    return null;
  }
}