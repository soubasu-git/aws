package org.ecnanif.feeder.repository;

import org.ecnanif.feeder.model.MF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DBFeederEventRepository extends JpaRepository<MF, BigInteger> {
}
