package org.ecnanif.db.repository;

import org.ecnanif.db.model.MF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface MFRepository extends JpaRepository<MF, BigInteger> {
}
