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
        mapping(bytes32 => uint) joinUser;
        mapping(uint=>bytes32) userlist;
        uint endTime;
        uint countJoin;
        bool kj;
    }

    uint public pid;
    address public chairperson;
    mapping(uint=>Proposal) public proposals;
    mapping(bytes32=>string)  user;
    constructor() {
        chairperson = msg.sender;
        pid=0;
    }
    // 会员注册
    function register(bytes32  passw  , string memory name ) public{
        require(
            msg.sender==chairperson &&
            bytes(user[passw]).length==0
        );
        user[passw]=name;
    }
    // 添加摇号
    function addProposals(string memory name  , uint  count,uint  endTime  ) public{
        require(
            msg.sender==chairperson &&
            endTime> block.timestamp ,"only charman can send add or time is old"
        );
        Proposal storage p=proposals[pid++];
        p.name=name;
        p.count=count;
        p.endTime=endTime;
        p.kj=false;
    }
    function joinRes(uint  resid  , bytes32 pwd  ) public{
        require(
            msg.sender==chairperson &&
            proposals[resid].endTime> block.timestamp &&
            pid>resid   ,"only charman can send add or Res is end or there no resid"
        );
        proposals[resid].joinUser[pwd]=1;
        proposals[resid].userlist[proposals[resid].countJoin++]=pwd;
    }
    function getAnswer(uint resid,bytes32 pwd) public  view returns( uint ans ) {
        require(
            msg.sender==chairperson
            && proposals[resid].endTime<= block.timestamp ,"only charman can send add or Res is not end"
        );
        return  proposals[resid].joinUser[pwd];
    }
    function kaijiang(uint resid) public  {
        require(
            msg.sender==chairperson && proposals[resid].kj==false
            && proposals[resid].endTime<= block.timestamp ,"only charman can send add or Res is not end"
        );
        uint i=0;
        proposals[resid].kj=true;
        while( i<proposals[resid].countJoin) {
            uint  index = uint256( keccak256(abi.encodePacked(block.timestamp)))%proposals[resid].countJoin;
            if(proposals[resid].joinUser[proposals[resid].userlist[index]]==1){
                proposals[resid].joinUser[proposals[resid].userlist[index]]=2;
                i++;

            }
        }

    }
}
