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
        checkpoints.put(20000, new Sha256Hash("0000000000ebc9dbdf2c952ceb83025ac6b130e7be41fda37d5c29cc78fb39d5"));
        checkpoints.put(50000, new Sha256Hash("150062ceb2a2fe00fe071a233aa4f61c9871cc9426a56718858aec583225caf2"));
        checkpoints.put(58000, new Sha256Hash("26e388e51f144a5bdaebfbe0b56d80237647b8bba0abec82e71ce09e492c13de"));

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
