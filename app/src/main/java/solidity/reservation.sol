// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

/** 
 * @title Ballot
 * @dev Implements voting process along with vote delegation
 */
contract reservation {

    struct  Proposal {
        uint pid;
        string name;   // short name (up to 32 bytes)
        uint count; // number of accumulated votes
        mapping(string => bool) list;
        uint endTime;
    }

    uint pid;
    address public chairperson;
    mapping(uint=>Proposal) public proposals;
    mapping(string=>string) public user;
    constructor() {
        chairperson = msg.sender;
        pid=0;
    }
    function register(string memory id  , string memory name ) public{
        require(
            bytes(user[id]).length==0
        );
        user[id]=name;
    }
    function addProposals(string memory name  , uint  count,uint  endTime  ) public{
        require(
            msg.sender==chairperson &&
            endTime> block.timestamp ,"only charman can send add or time is old"
        );
        Proposal storage p=proposals[pid++];
        p.name=name;
        p.count=count;
        p.endTime=endTime;
    }
}

web3j generate solidity  -a reservation_sol_reservation.abi -b reservation_sol_reservation.bin -o D:\IdeaProgram\IdeaProjects\MyApplication\app\src\main -p=com.example.myapplication

-h, --help                        Show this help message and exit.
-V, --version                     Print version information and exit.
-jt, --javaTypes                  use native java types. Default: true
-st, --solidityTypes              use solidity types.
-a, --abiFile=<abiFile>           abi file with contract definition.
-b, --binFile=<binFile>           optional bin file with contract compiled code in order to generate deploy methods.
-o, --outputDir=<destinationFileDir> destination base directory.
-p, --package=<packageName>       base package name.
