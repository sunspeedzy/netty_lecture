# -*- coding: utf-8 -*-
__author__ = "作者"

from py.thrift.generated import PersonService
from PersonServiceImpl import PersonServiceImpl

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from thrift.server import TServer

try:
    personServiceHandler = PersonServiceImpl()
    processor = PersonService.Processor(personServiceHandler)

    serverSocket = TSocket.TServerSocket(port=8899)
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TCompactProtocol.TCompactProtocolFactory()

    server = TServer.TSimpleServer(processor, serverSocket, transportFactory, protocolFactory)
    print("Thrift Server Started!")
    server.serve()
except Thrift.TException as ex:
    print("%s" % ex.message)
