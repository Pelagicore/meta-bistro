#
#   Copyright (c) 2019 Amazon.com, Inc.
#
#   SPDX-License-Identifier: Apache-2.0
#

require avs-device-sdk.inc

SRC_URI[md5sum] = "dbdb9767ec898e1b2ac0125f9412cb8e"
SRC_URI[sha256sum] = "354cff706c7c4c3ea4c8c5fb2efd2087df57a9b002956f9d68982de00617c913"

# Enable OPUS by default
PACKAGECONFIG += "opus kittai portaudio"
