# meta-bistro # 

A layer to provide IVI related functionality in an OpenEmbedded Linux system.

## DEPENDENCIES ##

This layer currently depends on: 
* poky (and meta-oe)
* meta-qt5
* meta-openembedded/meta-ruby
* meta-openembedded/meta-multimedia

## Layers ## 

Depending on included layers used together with meta-bistro additional 
functionality is added from the layers/*/ directories. This is done by adding 
the layers/* directory if the name is in the BBFILE_COLLECTIONS variable, as
defined in the conf/layer.conf file. 

The BBFILE_COLLECTIONS contains the names as given by each respective meta
layer, and hence the names of the directories can not be changed by us directly.

The general idea is that These layer additions should only be added to
meta-bistro if those other layers are available.

## LICENSE AND COPYRIGHT ##

Copyright (C) 2015 Pelagicore AB

All metadata is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in the associated
recipe (.bb file) unless otherwise stated.

License information for any other files is either explicitly stated 
or defaults to MIT license.
