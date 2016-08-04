#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Image for creating a small bootable Bistro-based image, the SDK and also make the sysroot ready for building Qt against it (with the SDK, not with bitbake)"

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


### extre qt build deps
IMAGE_INSTALL += "gpu-viv-bin-mx6q"

IMAGE_INSTALL += "libpng libudev glib-2.0 fontconfig freetype pulseaudio libpulse libjson libsndfile1 libxml2 alsa-lib jpeg libsqlite3 icu"
IMAGE_INSTALL += "gstreamer gst-meta-base gst-plugins-ugly gst-plugins-good gst-plugins-bad gst-plugins-base libfslcodec-meta"


### ncm for terminal-mode
MACHINE_EXTRA_RRECOMMENDS += "kernel-module-usbnet kernel-module-cdc_ncm"
