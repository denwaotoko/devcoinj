/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devcoin.params;

import com.google.devcoin.core.NetworkParameters;
import com.google.devcoin.core.Sha256Hash;
import com.google.devcoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(0x1d00ffffL);
        acceptableAddressCodes = new int[] { 0 };
        dumpedPrivateKeyHeader = 128;
        addressHeader = 0;
        port = 52333;
        packetMagic = 0x4445563AL;
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setTime(1311305081L);
        genesisBlock.setNonce(3085127155L);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 2147483647;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("0000000062558fec003bcbf29e915cddfc34fa257dc87573f28e4520d1c7c11e"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        //checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        //checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        //checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        //checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        //checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));

        dnsSeeds = new String[] {
                //"seed.devcoin.sipa.be",        // Pieter Wuille
                //"dnsseed.bluematt.me",         // Matt Corallo
                //"dnsseed.devcoin.dashjr.org",  // Luke Dashjr
                "dvcstable01.dvcnode.org",        // Pieter Wuille
                "dvcstable02.dvcnode.org",
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }
}
