#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Image for creating a small bootable Bistro-based image, the SDK and also make the sysroot ready for building Qt against it (with the SDK, not with bitbake). Also including wayland and meta-ivi packages"

inherit core-image


### copy from core-image-bistro.bb without lamprey
# Network management
IMAGE_INSTALL += "connman"

# helpers (dev)
IMAGE_FEATURES += "package-management"

# systemd units
IMAGE_INSTALL += "systemd-additional-units"

# No need for too much space right now, but some extra is always nice.
IMAGE_ROOTFS_SIZE = "1000000"

IMAGE_FSTYPES = "ext3 sdcard"


### copy from core-image-bistro-dev.bb
IMAGE_INSTALL += "packagegroup-bistro-utils git"

# helpers (dev)
IMAGE_FEATURES += "ssh-server-openssh tools-debug"
IMAGE_INSTALL += "openssh-sftp-server"


### enable font deployment
IMAGE_INSTALL += "liberation-fonts ttf-bitstream-vera"


### extra qt build deps
IMAGE_INSTALL += "gpu-viv-bin-mx6q"
TOOLCHAIN_TARGET_TASK += "gpu-viv-bin-mx6q-dev libgl-mx6-dev libclc-mx6-dev libgles2-mx6-dev libopenvg-mx6-dev libvdk-mx6-dev libegl-mx6-dev libwayland-egl-mx6-dev gpu-viv-bin-mx6q-dev"

IMAGE_INSTALL += "libpng libudev glib-2.0 fontconfig freetype pulseaudio libpulse libsndfile1 libxml2 alsa-lib jpeg libsqlite3 icu"

IMAGE_INSTALL += "gstreamer gst-plugins-good gst-plugins-base gst-meta-base gst-plugins-base-meta gst-plugins-good-meta gst-plugins-bad-meta"
IMAGE_INSTALL += "libfslcodec-meta gst-fsl-plugin libfslcodec libfslparser libfslvpuwrap"

### force wayland libs to be installed on the target (might happen via DISTRO_FEATURE and resulting deps as well)
IMAGE_INSTALL += "wayland"


### Additional libs to complete MirroLink offering
IMAGE_INSTALL += "busybox-udhcpd tcpdump wpa-supplicant libusb1 libxml2 expat openssl iptables"
IMAGE_INSTALL += "avahi pulseaudio-server alsa-lib-dev"
IMAGE_INSTALL += "libusb-compat"
IMAGE_INSTALL += "libudev"


### .. and adding boost :)
IMAGE_INSTALL += "boost"


IMAGE_INSTALL += "iproute2"

### basic can libs and ncm for terminal-mode
IMAGE_INSTALL += "canutils libsocketcan"
MACHINE_EXTRA_RRECOMMENDS += "kernel-module-usbnet kernel-module-cdc_ncm kernel-module-g_ncm kernel-module-iowarrior"
IMAGE_INSTALL += "kernel-module-pcan"
