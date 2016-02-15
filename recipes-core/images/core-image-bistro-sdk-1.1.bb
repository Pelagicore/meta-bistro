#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Image for creating a small bootable Bistro-based image"

inherit core-image
inherit populate_sdk_qt5_qtcreator

# Network management
IMAGE_INSTALL += "connman"

# helpers (dev)
IMAGE_FEATURES += "package-management"

# systemd units
IMAGE_INSTALL += "systemd-additional-units"

TOOLCHAIN_HOST_TASK += "nativesdk-cmake"

# Add "/usr/lib/cmake" to the PATH variable so that CMake can find the *Config.cmake" when FIND_PACKAGE() is called from a CMake makefile
toolchain_create_sdk_env_script_append() {
	echo 'export PATH=$PATH:$SDKTARGETSYSROOT/usr/lib/cmake' >> $script
}

# No need for too much space right now, but some extra is always nice. 
IMAGE_ROOTFS_SIZE ?= "1000000"

IMAGE_FSTYPES ?= "ext3 sdcard"
