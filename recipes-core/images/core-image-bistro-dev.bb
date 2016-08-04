#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Image for creating a small bootable Bistro-based image during development"

require core-image-bistro.bb

IMAGE_INSTALL += "packagegroup-bistro-utils git systemd-analyze"

# helpers (dev)
IMAGE_FEATURES += "ssh-server-openssh tools-debug"
IMAGE_INSTALL += "openssh-sftp-server"
