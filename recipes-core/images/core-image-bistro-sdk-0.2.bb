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

IMAGE_INSTALL += "libpng libudev glib-2.0 fontconfig freetype pulseaudio libpulse libjson libsndfile1 libxml2 alsa-lib jpeg libsqlite3 icu"

IMAGE_INSTALL += "gstreamer gst-meta-base gst-plugins-base-meta gst-plugins-good-meta gst-plugins-bad-meta"
IMAGE_INSTALL += "libfslcodec-meta gst-fsl-plugin libfslcodec libfslparser libfslvpuwrap"


### basic can libs and ncm for terminal-mode
IMAGE_INSTALL += "canutils libsocketcan"
MACHINE_EXTRA_RRECOMMENDS += "kernel-module-usbnet kernel-module-cdc_ncm kernel-module-g_ncm"


### force wayland libs to be installed on the target (might happen via DISTRO_FEATURE and resulting deps as well)
IMAGE_INSTALL += "wayland"


### install meta-ivi package groups (p1 and p2)
IMAGE_INSTALL += "packagegroup-specific-component-p1 packagegroup-specific-component-p2"


### Navit-POC dependencies
#IMAGE_INSTALL += "libdbus-c++"
