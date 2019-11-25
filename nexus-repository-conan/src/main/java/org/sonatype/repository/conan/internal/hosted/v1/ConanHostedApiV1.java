package org.sonatype.repository.conan.internal.hosted.v1;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.repository.view.Router;
import org.sonatype.repository.conan.internal.common.PingController;
import org.sonatype.repository.conan.internal.common.UserController;

import static com.google.common.base.Preconditions.checkNotNull;

@Named
@Singleton
public class ConanHostedApiV1
    extends ComponentSupport
{
  private static final String VERSION = "v1";

  private PingController pingController;

  private UserController userController;

  private ConanHostedControllerV1 conanHostedControllerV1;

  private HostedHandlers hostedHandlers;

  @Inject
  public ConanHostedApiV1(final PingController pingController,
                          final UserController userController,
                          final ConanHostedControllerV1 conanHostedControllerV1,
                          final HostedHandlers hostedHandlers)
  {
    this.pingController = checkNotNull(pingController);
    this.userController = checkNotNull(userController);
    this.conanHostedControllerV1 = checkNotNull(conanHostedControllerV1);
    this.hostedHandlers = checkNotNull(hostedHandlers);
  }

  public void create(final Router.Builder builder) {
    pingController.attach(builder, VERSION);
    userController.attach(builder, VERSION);
    conanHostedControllerV1.attach(builder);
  }
}
