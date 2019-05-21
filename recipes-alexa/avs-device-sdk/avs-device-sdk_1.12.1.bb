#
#   Copyright (c) 2019 Amazon.com, Inc.
#
#   SPDX-License-Identifier: Apache-2.0
#

require avs-device-sdk.inc

SRC_URI[md5sum] = "ece2fda85607567dccb3163bf9311efb"
SRC_URI[sha256sum] = "fba17b343c011410584330d8e268d09f449d16d62007b1a797751721a5616368"

# Enable OPUS by default
PACKAGECONFIG += "opus kittai portaudio"
