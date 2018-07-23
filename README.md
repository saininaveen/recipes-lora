# Raspberry Pi + Yocto + LoRa
# recipes-lora
Yocto recipe to build and install https://github.com/Lora-net/lora_gateway package into Yocto image.


This recipe is written for hobby purpose, as I needed to control LoRa(iC880A-SPI) which is connected to
raspberrypi2. Raspberry pi is hosting lighweight version of Yocto.

To include raspberry pi BSP layer, follow this link:
https://git.yoctoproject.org/cgit.cgi/meta-raspberrypi/about/


To build:

	$ cd ~/your_workspace (/home/<user>/yoctoproject/yocto)

	Checkout Poky source
	$ git://git.yoctoproject.org/poky
	$ cd poky

	Checkout Dependencies
	$ git checkout git://git.openembedded.org/meta-openembedded

	Checkout raspberrypi BSP layer
	$ git checkout https://github.com/agherzan/meta-raspberrypi

	Checkout LoRa recipe
	$ cd meta-raspberrypi/
	$ git checkout https://github.com/saininaveen/recipes-lora.git



	$ source poky/oe-init-build-env rpi-build	
	

	Update conf/local.conf
	--------------------------------------
	MACHINE ??= "raspberrypi2"
	ENABLE_SPI_BUS = "1"
	.
	.

	IMAGE_INSTALL_append = " lora-gateway"



	Update conf/bblayers.conf
	--------------------------------------

	BBLAYERS ?= " \
	  /home/<user>/yoctoproject/yocto/poky/meta \
	  /home/<user>/yoctoproject/yocto/poky/meta-poky \
	  /home/<user>/yoctoproject/yocto/poky/meta-yocto-bsp \
	  /home/<user>/yoctoproject/yocto/poky/meta-openembedded/meta-oe \
	  /home/<user>/yoctoproject/yocto/poky/meta-openembedded/meta-multimedia \
	  /home/<user>/yoctoproject/yocto/poky/meta-openembedded/meta-networking \
	  /home/<user>/yoctoproject/yocto/poky/meta-openembedded/meta-python \
	  /home/<user>/yoctoproject/yocto/poky/meta-raspberrypi \
	  "

	Trigger the build:
	$ bitbake rpi-hwup-image

	Write to sdcard:
	dd to a SD card the generated sdimg file	


	LoRa binaries will be written at /opt/ location:
	

	To Run the LoRa apps, follow below link:
	https://webshop.ideetron.nl/Files/3/1000/1211/Attachments/Product/9Sl3U5tf7B238WGCZ1V7PRmw2768t90K.pdf

	For example:

	To send some data:
	$ cd /opt/lora_gateway/util_tx_test
	$ ./util_tx_test –r 1257 –f 866.5


	To receive lora packets:
	$ cd /opt/lora_gateway/util_pkt_logger
	$ ./util_ptk_logger




Reference Links:
https://git.yoctoproject.org/cgit.cgi/meta-raspberrypi/about/

https://github.com/Lora-net/lora_gateway

https://webshop.ideetron.nl/Files/3/1000/1211/Attachments/Product/9Sl3U5tf7B238WGCZ1V7PRmw2768t90K.pdf

	


